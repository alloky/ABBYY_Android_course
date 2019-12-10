package task_4

import kotlin.IndexOutOfBoundsException as IndexOutOfBoundsException1

class MyArrayList<E> : RandomAccess {
    private var len : Int = 0;
    private var capacity : Int = 0;
    private var buffer = arrayOfNulls<Any>(0);

    private fun extend(){
        var newLen = capacity * 2;
        if (len == 0){
            newLen = 32;
        }
        buffer = buffer.copyOf(newLen);
        capacity = newLen;
    }

    public fun add(elem: E) {
        if (len == capacity){
            extend()
        }

        buffer[len] = elem
        ++len
        return
    }


    public fun insert(elem: E, pos: Int) {
        if (len == capacity){
            extend()
        }

        for (i in pos until len){
            buffer[pos + 1] = buffer[pos]
        }

        buffer[pos] = elem

        ++len
    }

    public fun delete(pos: Int){
        if (pos >= len || pos < 0){
            throw IndexOutOfBoundsException("MyArrayList index out of bounds")
        }

        for (i in pos + 1 until len){
            buffer[i - 1] = buffer[i]
        }

        buffer[len - 1] = null

        --len
    }

    operator fun get(pos: Int) = buffer[pos]

    operator fun set(pos: Int, elem: E) {
        buffer[pos] = elem
    }

}
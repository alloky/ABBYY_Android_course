package com.example.task_6

import android.graphics.drawable.Icon
import android.media.Image
import java.util.*

class NewsPreview(header:String, dt: Date, descr: String, imId: Int) {
    public var header: String = header
    public var date: Date = dt
    public var description: String = descr
    public var imgId: Int = imId;
}
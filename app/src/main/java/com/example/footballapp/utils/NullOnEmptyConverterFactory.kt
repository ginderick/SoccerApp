package com.example.footballapp.utils
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


val nullOnEmptyConverterFactory = object : Converter.Factory() {
    fun converterFactory() = this
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ) = object : Converter<ResponseBody, Any?> {
        val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(
            converterFactory(),
            type,
            annotations
        )
        override fun convert(value: ResponseBody) = if (value.contentLength() != 0L) nextResponseBodyConverter.convert(
            value
        ) else null
    }
}


//class NullOnEmptyConverterFactory : Converter.Factory() {
//    override fun responseBodyConverter(
//        type: Type,
//        annotations: Array<Annotation>,
//        retrofit: Retrofit
//    ): Converter<ResponseBody, *> {
//        val delegate: Converter<ResponseBody, *> =
//            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
//        return Converter { body -> if (body.contentLength() == 0L) null else delegate.convert(body) }
//    }
//}
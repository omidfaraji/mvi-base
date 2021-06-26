package com.faraji.mvibase.example.utils

import com.faraji.mvibase.example.utils.extension.getSerializedNameValue
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class EnumRetrofitConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? {
        if (type is Class<*> && type.isEnum) {
            return Converter<Enum<*>, String> { it.getSerializedNameValue() }
        }
        return null
    }
}

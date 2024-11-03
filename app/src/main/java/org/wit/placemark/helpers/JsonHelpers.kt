package org.wit.placemark.helpers

import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

// General Gson builder for serialization/deserialization
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()

// General list type function for any model
inline fun <reified T> listType(): Type = object : TypeToken<ArrayList<T>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

// Uri parser for handling Uri objects in JSON
class UriParser : JsonDeserializer<Uri>, JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}

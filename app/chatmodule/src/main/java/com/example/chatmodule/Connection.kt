package com.example.chatmodule

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

object Connection {
    const val url = "ws://95.31.130.149:8085"
    val callbacks: MutableList<Callback> = mutableListOf()
    val client = object: WebSocketClient(URI("$url/chat"), mapOf("idUser" to "6")) {
        override fun onOpen(handshakedata: ServerHandshake?) {
            callbacks.forEach {
                it.onOpen()
            }
        }

        override fun onMessage(message: String) {
            if ("\"type\": \"message\"" in message) {
                val modelMessage = Gson().fromJson<ModelResponse<Message>>(message, object:
                    TypeToken<ModelResponse<Message>>(){}.type).body
                callbacks.forEach {
                    it.onMessage(modelMessage)
                }
            }
        }

        override fun onClose(code: Int, reason: String?, remote: Boolean) {}

        override fun onError(ex: Exception?) {}

    }
}
interface Callback{
    fun onOpen()
    fun onMessage(message: Message)
    fun onChat(chat: DataChat)
    fun onChats(chats: List<Chat>)
    fun onPerson(person: User)
}

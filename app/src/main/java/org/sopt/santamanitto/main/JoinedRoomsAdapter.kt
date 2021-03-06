package org.sopt.santamanitto.main

import android.view.ViewGroup
import org.sopt.santamanitto.room.data.JoinedRoom
import org.sopt.santamanitto.view.recyclerview.BaseAdapter
import org.sopt.santamanitto.view.recyclerview.BaseViewHolder
import org.sopt.santamanitto.room.network.RoomRequest
import org.sopt.santamanitto.user.data.source.UserDataSource

class JoinedRoomsAdapter(
    private val cachedUserDataSource: UserDataSource,
    private val roomRequest: RoomRequest
) : BaseAdapter<JoinedRoom>() {

    private var listener: ((roomId: Int, isMatched: Boolean, isFinished: Boolean) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<JoinedRoom, *> {
        return JoinedRoomViewHolder(parent, cachedUserDataSource, roomRequest, listener)
    }

    fun setOnItemClickListener(listener: (roomId: Int, isMatched: Boolean, isFinished: Boolean) -> Unit) {
        this.listener = listener
    }
}
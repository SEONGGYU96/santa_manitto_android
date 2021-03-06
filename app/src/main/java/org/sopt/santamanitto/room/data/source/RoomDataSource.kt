package org.sopt.santamanitto.room.data.source

import org.sopt.santamanitto.room.data.PersonalRoomInfo

interface RoomDataSource {
    interface GetPersonalRoomInfoCallback {
        fun onLoadPersonalRoomInfo(personalRoomInfo: PersonalRoomInfo)

        fun onDataNotAvailable()
    }

    fun getPersonalRoomInfo(roomId: Int, callback: GetPersonalRoomInfoCallback)

}
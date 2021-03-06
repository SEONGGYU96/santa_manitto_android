package org.sopt.santamanitto.room.create.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import org.sopt.santamanitto.NetworkViewModel
import org.sopt.santamanitto.room.create.data.CreateMissionLiveList
import org.sopt.santamanitto.room.create.network.CreateRoomData
import org.sopt.santamanitto.room.create.data.ExpirationLiveData
import org.sopt.santamanitto.room.network.RoomRequest
import org.sopt.santamanitto.room.create.network.CreateRoomResponse
import org.sopt.santamanitto.room.data.source.CachedRoomDataSource
import org.sopt.santamanitto.user.data.source.UserCachedDataSource
import org.sopt.santamanitto.user.data.source.UserDataSource
import javax.inject.Named

class CreateRoomAndMissionViewModel @ViewModelInject constructor(
        @Named("cached") private val userDataSource: UserDataSource,
        private val roomRequest: RoomRequest
) : NetworkViewModel() {

    val expirationLiveData = ExpirationLiveData()

    var roomName = MutableLiveData<String?>(null)

    val missions = CreateMissionLiveList()

    val missionIsEmpty = Transformations.map(missions) {
        it.isEmpty()
    }

    var heightOfRecyclerView: Int = 0

    var nameIsNullOrEmpty = Transformations.map(roomName) {
        it.isNullOrBlank()
    }

    fun setDayDiff(dayDIff: Int) {
        expirationLiveData.dayDiff = dayDIff
    }

    fun setAmPm(isAm: Boolean) {
        expirationLiveData.setAmPm(isAm)
    }

    fun setTime(hour: Int, minute: Int) {
        expirationLiveData.setTime(hour, minute)
    }

    fun addMission(mission: String) {
        missions.addMission(mission)
    }

    fun deleteMission(mission: String) {
        missions.deleteMission(mission)
    }

    fun hasMissions(): Boolean {
        return missions.getMissions().isNotEmpty()
    }

    fun clearMission() {
        missions.clear()
    }

    fun createRoom(callback: (CreateRoomResponse) -> Unit) {
        val createRoomData = CreateRoomData(roomName.value!!, expirationLiveData.toString(), missions.getMissions())
        roomRequest.createRoom(createRoomData, object : RoomRequest.CreateRoomCallback {
            override fun onRoomCreated(createdRoom: CreateRoomResponse) {
                callback(createdRoom)
            }

            override fun onFailed() {
                _networkErrorOccur.value = true
            }
        })

        (userDataSource as UserCachedDataSource).isJoinedRoomDirty = true
    }
}
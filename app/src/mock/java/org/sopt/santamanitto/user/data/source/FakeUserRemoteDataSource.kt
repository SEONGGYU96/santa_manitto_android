package org.sopt.santamanitto.user.data.source

import org.sopt.santamanitto.room.data.JoinedRoom
import org.sopt.santamanitto.user.data.LoginUser
import org.sopt.santamanitto.user.data.User

class FakeUserRemoteDataSource : UserDataSource {

    private val fakeLoginUser = LoginUser(
            "fakeUser",
            "f0a1k2e3u4e5s6e7r8",
            1,
            "face1access2token3"
    )

    private val fakeJoinedRooms = mutableListOf<JoinedRoom>().apply {
        add(JoinedRoom(1, "fakeRoom1", false,
                "2021-02-15 12:33:44", "2021-01-25 12:33:44"))

        add(JoinedRoom(2, "fakeRoom2", false,
                "2021-02-10 12:33:44", "2021-01-24 12:33:44"))

        add(JoinedRoom(3, "fakeRoom3", true,
                "2021-01-30 12:33:44", "2021-01-20 12:33:44"))

        add(JoinedRoom(4, "fakeRoom4", true,
                "2021-01-14 12:33:44", "2021-01-12 12:33:44"))

        add(JoinedRoom(5, "fakeRoom5", true,
                "2021-01-11 12:33:44", "2021-01-09 12:33:44"))
    }

    private val fakeUsers = HashMap<Int, User>().apply {
        put(1, User(1, "fakeUser1", mutableListOf()))
        put(2, User(2, "fakeUser2", mutableListOf()))
        put(3, User(3, "fakeUser3", mutableListOf()))
        put(4, User(4, "fakeUser4", mutableListOf()))
        put(5, User(5, "fakeUser5", mutableListOf()))
        put(6, User(6, "fakeUser6", mutableListOf()))
    }

    override fun login(serialNumber: String, callback: UserDataSource.LoginCallback) {
        callback.onLoginSuccess(fakeLoginUser)
    }

    override fun createAccount(
            userName: String,
            serialNumber: String,
            callback: UserDataSource.CreateAccountCallback
    ) {
        callback.onCreateAccountSuccess(fakeLoginUser)
    }

    override fun getUserId(): Int {
        return 0
    }

    override fun getAccessToken(): String {
        return ""
    }

    override fun getUserName(): String {
        return ""
    }

    override fun getJoinedRoom(userId: Int, callback: UserDataSource.GetJoinedRoomsCallback) {
        callback.onJoinedRoomsLoaded(fakeJoinedRooms)
    }

    override fun getUserInfo(userId: Int, callback: UserDataSource.GetUserInfoCallback) {
        if (fakeUsers.containsKey(userId)) {
            callback.onUserInfoLoaded(fakeUsers[userId]!!)
        } else {
            callback.onDataNotAvailable()
        }
    }
}
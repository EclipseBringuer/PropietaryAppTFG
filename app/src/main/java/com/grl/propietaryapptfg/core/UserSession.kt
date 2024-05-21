package com.grl.propietaryapptfg.core

import com.grl.propietaryapptfg.data.models.UserModel

class UserSession {
    companion object {
        private var user: UserModel? = null

        fun setUser(user: UserModel?) {
            this.user = user
        }

        fun getUser(): UserModel? {
            return this.user
        }
    }
}
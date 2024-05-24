package com.grl.propietaryapptfg.core

class Constants {
    companion object {
        const val TOKEN = "15112004"
        const val CARD = "CARD"
        const val PICK = "PICK"
        const val DELIVERY = "DELIVERY"
        const val PENDING = "PENDING"
        const val PREPARATION = "PREPARATION"
        const val COMPLETED = "COMPLETED"
        const val CANCELED = "CANCELED"

        class Tabs{
            companion object{
                private const val PENDINGS = "Pendientes"
                private const val ACCEPTEDS = "Aceptados"

                fun getListOfTabs():List<String>{
                    return listOf(PENDINGS, ACCEPTEDS)
                }
            }
        }
    }
}
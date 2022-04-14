package com.sokolovds.myapplication.utils.activityUtils

typealias ActivityAction<T> = (T) -> Unit

/**
 * Выполнение действий внутри активити, в зависимости от ее жизненного цикла
 *
 * T - тип нашей активити
 */
class ActivityActionsManager<T> {

    private var activity: T? = null
        set(value) {
            field = value
            if (value != null) {
                actionsQueue.forEach {
                    it(value)
                    clearActionsQueue()
                }

            }
        }

    private val actionsQueue: MutableList<ActivityAction<T>> = mutableListOf()

    operator fun invoke(activityAction: ActivityAction<T>) {
        val activity = this.activity
        if (activity != null) {
            activityAction(activity)
        } else {
            actionsQueue.add(activityAction)
        }
    }

    fun setActive(activity: T) {
        this.activity = activity
    }

    fun setUnActive() {
        this.activity = null
    }

    fun clearActionsQueue() {
        actionsQueue.clear()
    }

}
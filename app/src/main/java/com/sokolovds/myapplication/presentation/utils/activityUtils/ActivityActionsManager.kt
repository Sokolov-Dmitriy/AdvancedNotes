package com.sokolovds.myapplication.presentation.utils.activityUtils

typealias ResourceAction<T> = (T) -> Unit

/**
 * Выполнение действий внутри активити, в зависимости от ее жизненного цикла
 *
 * T - тип нашей активити
 */
class ResourceActionsManager<T> {

    var resource: T? = null
        set(value) {
            field = value
            if (value != null) {
                actionsQueue.forEach {
                    it(value)
                    clearActionsQueue()
                }

            }
        }

    private val actionsQueue: MutableList<ResourceAction<T>> = mutableListOf()

    operator fun invoke(resourceAction: ResourceAction<T>) {
        val resource = this.resource
        if (resource != null) {
            resourceAction(resource)
        } else {
            actionsQueue.add(resourceAction)
        }
    }

    fun clearActionsQueue() {
        actionsQueue.clear()
    }


}
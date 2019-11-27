package dev.engel.projectoxygen.core.resources

import android.content.Context
import androidx.annotation.StringRes

/**
 * Facade for providing string resources. This is primarily useful when the class which needs to
 * display strings also needs to be tested.
 */
interface StringProvider {

    /**
     * @param resourceId - the corresponding resource id for the expected string.
     *
     * @return the [String] which corresponds to the provided [resourceId].
     */
    fun get(@StringRes resourceId: Int): String

    /**
     * @param resourceId - the corresponding resource id for the expected string.
     * @param formatArguments - the arguments which should be used to format the string.
     *
     * @return the [String] which corresponds to the provided [resourceId] and will be formatted
     * with the given [formatArguments].
     */
    fun get(@StringRes resourceId: Int, vararg formatArguments: Any): String

    /**
     * This is a convenience
     *
     * @param resourceId - the corresponding resource id for the expected string.
     * @param formatArguments - the arguments which should be used to format the string.
     *
     * @return the [String] which corresponds to the provided [resourceId] and will be formatted
     * with the given [formatArguments].
     */
    fun get(@StringRes resourceId: Int, formatArguments: List<Any>): String

    companion object {

        /**
         * Provides an implementation of [StringProvider] which is backed by the Android [Context],
         * specifically the application context.
         *
         * @param context - the application's context to use to get strings.
         */
        operator fun invoke(context: Context): StringProvider {
            val applicationContext = context.applicationContext
            return object : StringProvider {
                override fun get(resourceId: Int): String {
                    return applicationContext.getString(resourceId)
                }

                override fun get(resourceId: Int, vararg formatArguments: Any): String {
                    return applicationContext.getString(resourceId, *formatArguments)
                }

                override fun get(resourceId: Int, formatArguments: List<Any>): String {
                    return get(resourceId, *formatArguments.toTypedArray())
                }

            }
        }
    }
}
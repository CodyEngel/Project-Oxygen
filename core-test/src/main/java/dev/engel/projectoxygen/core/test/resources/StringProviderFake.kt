package dev.engel.projectoxygen.core.test.resources

import dev.engel.projectoxygen.core.resources.StringProvider
import dev.engel.projectoxygen.core.test.internal.randomString

/**
 * Provides a fake implementation of [StringProvider] which is intended to make writing unit tests
 * easier.
 */
class StringProviderFake : StringProvider {

    private val expectedStrings = mutableMapOf<Int, String>()

    /**
     * Notifies [StringProviderFake] that the given [stringResValue] is expected during the test and
     * it will be provided in the corresponding [get] functions when invoked.
     */
    fun expect(stringResValue: StringResValue) {
        expectedStrings[stringResValue.resourceId] = stringResValue.expectedValue
    }

    override fun get(resourceId: Int): String {
        return expectedStrings[resourceId] ?: throw StringResValueNotFoundException(resourceId)
    }

    override fun get(resourceId: Int, vararg formatArguments: Any): String {
        return expectedStrings[resourceId]?.format(*formatArguments) ?:
            throw StringResValueNotFoundException(resourceId)
    }

    override fun get(resourceId: Int, formatArguments: List<Any>): String {
        return get(resourceId, *formatArguments.toTypedArray())
    }

}

/**
 * StringResValue encapsulates the relationships between a [resourceId] and [expectedValue]. If the
 * desired [resourceId] should also include string formatting it is recommended to use an
 * [expectedValue] which includes those format placeholders.
 */
class StringResValue(
    val resourceId: Int,
    val expectedValue: String = randomString()
)

/**
 * This exception is thrown when an attempt to get a string is done without first expecting the
 * [StringResValue] would be needed.
 */
class StringResValueNotFoundException(
    resourceId: Int
) : RuntimeException(FORMATTED_MESSAGE.format(resourceId)) {
    companion object {
        const val FORMATTED_MESSAGE = "Tried to get a string with resource id: %d, but was not " +
                "found. Did you remember to call StringProviderFake#expect before running this test?"
    }
}
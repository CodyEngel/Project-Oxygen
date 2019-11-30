package dev.engel.projectoxygen.core.resources

import android.content.Context
import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class StringProviderTest {

    private val context: Context = mockk<Context>().also { mockedContext ->
        every { mockedContext.applicationContext } returns mockedContext
    }

    private val subject: StringProvider = StringProvider(context)

    private val resourceId = 1
    private val formatArguments = listOf("hey", 51, 'n', 3.14)
    private val expectedString = "this is the expected string"

    @Test
    fun `given a string exists for a resource id, when resource id is provided, then the string should be returned`() {
        every { context.getString(resourceId) } returns expectedString

        assertThat(subject.get(resourceId)).isEqualTo(expectedString)
    }

    @Test
    fun `given a string exists for a resource id, when format arguments are provided, then the string should be returned`() {
        every { context.getString(resourceId, *formatArguments.toTypedArray()) } returns expectedString

        assertThat(subject.get(resourceId, *formatArguments.toTypedArray())).isEqualTo(expectedString)
    }

    @Test
    fun `given a string exists for a resource id, when format arguments are provided as list, then the string should be returned`() {
        every { context.getString(resourceId, *formatArguments.toTypedArray()) } returns expectedString

        assertThat(subject.get(resourceId, formatArguments)).isEqualTo(expectedString)
    }
}
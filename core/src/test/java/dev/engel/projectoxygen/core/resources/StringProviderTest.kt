package dev.engel.projectoxygen.core.resources

import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.engel.projectoxygen.core.resources.testcommons.every
import dev.engel.projectoxygen.core.resources.testcommons.mockContext
import dev.engel.projectoxygen.core.resources.testcommons.returns
import dev.engel.projectoxygen.core.resources.testcommons.whenever
import org.junit.Before
import org.junit.Test

class StringProviderTest {
    private val context = mockContext()

    private lateinit var subject: StringProvider

    private val resourceId = 1
    private val formatArguments = listOf("hey", 51, 'n', 3.14)
    private val expectedString = "this is the expected string"

    @Before
    fun setUp() {
        whenever(context.applicationContext).thenReturn(context)
        subject = StringProvider(context)
    }

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
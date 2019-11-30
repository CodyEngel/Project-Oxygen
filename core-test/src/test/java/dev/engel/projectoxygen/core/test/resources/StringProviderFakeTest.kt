package dev.engel.projectoxygen.core.test.resources

import assertk.assertThat
import assertk.assertions.*
import dev.engel.projectoxygen.core.test.resources.StringResValueNotFoundException.Companion.FORMATTED_MESSAGE
import org.junit.Test

class StringProviderFakeTest {

    private val subject: StringProviderFake = StringProviderFake()

    private val resourceId = 1
    private val stringResValue = StringResValue(resourceId = resourceId)
    private val expectedValue = stringResValue.expectedValue

    @Test
    fun `given a string does not exist for resource id, when resource id is provided, then an exception should be thrown`() {
        assertThat {
            subject.get(resourceId)
        }.isFailure().isInstanceOf(Throwable::class.java)
    }

    @Test
    fun `given a string does not exist for resource id, when resource id is provided, then the correct exception should be thrown`() {
        assertThat {
            subject.get(resourceId)
        }.isFailure().isInstanceOf(StringResValueNotFoundException::class.java)
    }

    @Test
    fun `given a string does not exist for resource id, when resource id is provided, then the correct cause should be provided`() {
        assertThat {
            subject.get(resourceId)
        }.isFailure().hasMessage(FORMATTED_MESSAGE.format(resourceId))
    }

    @Test
    fun `given a string does not exist for resource id, when resource id is provided with arguments, then an exception should be thrown`() {
        assertThat {
            subject.get(resourceId, "hey", 23)
        }.isFailure().isInstanceOf(Throwable::class.java)
    }

    @Test
    fun `given a string does not exist for resource id, when resource id is provided with arguments, then the correct exception should be thrown`() {
        assertThat {
            subject.get(resourceId, "hey", 23)
        }.isFailure().isInstanceOf(StringResValueNotFoundException::class.java)
    }

    @Test
    fun `given a string does not exist for resource id, when resource id is provided with arguments, then the correct cause should be provided`() {
        assertThat {
            subject.get(resourceId, "hey", 23)
        }.isFailure().hasMessage(FORMATTED_MESSAGE.format(resourceId))
    }

    @Test
    fun `given a string exists for a resource id, when resource id is provided, then the string should be returned`() {
        subject.expect(stringResValue)

        assertThat(subject.get(resourceId)).isEqualTo(expectedValue)
    }

    @Test
    fun `given a string exists for a resource id, when format arguments are provided, then the string should be returned`() {
        subject.expect(stringResValue)

        assertThat(subject.get(resourceId, "hello", 42)).isEqualTo(expectedValue)
    }

    @Test
    fun `given a string exists for a resource id, when format arguments are provided as list, then the string should be returned`() {
        subject.expect(stringResValue)

        assertThat(subject.get(resourceId, listOf("hello", 42))).isEqualTo(expectedValue)
    }

    @Test
    fun `given a formatted string exists for a resource id, when format arguments are provided, then the formatted string should be returned`() {
        val formattedStringResValue = StringResValue(resourceId, "%s %d")
        subject.expect(formattedStringResValue)

        assertThat(subject.get(resourceId, "hello", 42)).isEqualTo("hello 42")
    }
}
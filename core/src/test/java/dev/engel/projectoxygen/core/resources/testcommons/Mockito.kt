package dev.engel.projectoxygen.core.resources.testcommons

import org.mockito.Mockito.`when`
import org.mockito.stubbing.OngoingStubbing

/**
 * Proxies to Mockito's when function since the when keyword is reserved with Kotlin.
 */
fun <T> whenever(methodCall: T): OngoingStubbing<T> {
    return `when`(methodCall)
}

/**
 * Proxies to [whenever] in order to provide a more concise option for starting a mock chain. This
 * is intended to replicate MockK's every function.
 */
fun <T> every(methodCall: () -> T): OngoingStubbing<T> {
    return whenever(methodCall())
}

/**
 * Proxies to Mockito's thenReturn function while adding the infix keyword allowing for cleaner
 * definitions for mocks. This is intended to replicate MockK's returns function.
 */
infix fun <T> OngoingStubbing<T>.returns(returnValue: T) {
    thenReturn(returnValue)
}
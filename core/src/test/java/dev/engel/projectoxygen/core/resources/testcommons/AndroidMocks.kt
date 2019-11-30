package dev.engel.projectoxygen.core.resources.testcommons

import android.content.Context
import org.mockito.Mockito.mock

/**
 * @return a [mock] version of an Android [Context].
 */
fun mockContext(): Context = mock(Context::class.java)
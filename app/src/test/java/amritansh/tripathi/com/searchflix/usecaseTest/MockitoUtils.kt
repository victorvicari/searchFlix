package amritansh.tripathi.com.searchflix.usecaseTest

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

/**
 * Created by amritanshtripathi on 3/5/18.
 */
inline fun <T> whenever(methodCall: T) : OngoingStubbing<T> = Mockito.`when`(methodCall)
package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Learn Java", appName)
  }

  @Test
  fun `verify java execution engine executes println`() {
    val code = "public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Hello Awiskar\");\n    }\n}"
    val result = com.example.data.JavaExecutionEngine.execute(code)
    assertEquals(true, result.isSuccess)
    assertEquals("Hello Awiskar", result.stdout.trim())
  }
}

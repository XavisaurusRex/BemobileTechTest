package cat.devsofthecoast.bemobiletechtest.common.di.activity

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    companion object {

        @Provides
        fun layoutInflater(activity: Activity) = LayoutInflater.from(activity)

        @Provides
        fun appCompatActivity(activity: Activity) = activity as AppCompatActivity

    }
}
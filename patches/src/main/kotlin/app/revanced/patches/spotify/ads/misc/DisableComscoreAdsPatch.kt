package app.revanced.patches.spotify.ads.misc

// These are the imports that I failed to provide.
// They tell the compiler where to find all the annotations and classes.
import app.revanced.patcher.annotation.Description
import app.revanced.patcher.annotation.Name
import app.revanced.patcher.annotation.Patch
import app.revanced.patcher.annotation.Target
import app.revanced.patcher.annotation.hook.Hook
import app.revanced.patcher.annotation.hook.Hook.HookType
import app.revanced.patcher.extensions.RVParam
import app.revanced.patcher.util.Logger

@Patch
@Name("disable-comscore-ads")
@Description("Disables advertisements by neutralizing the Comscore analytics SDK.")
object DisableComscoreAdsPatch {

    @Target(
        value = "com.comscore.android.p002id.IdHelperAndroid",
        method = "isAdvertisementIdValid",
        returnType = Boolean::class,
        parameterTypes = [String::class]
    )
    @Hook(HookType.REPLACE)
    @JvmStatic
    fun alwaysInvalid(param: RVParam): Boolean {
        Logger.i("Comscore ad validation hook triggered: Forcing ad to be invalid.")
        return false
    }
}

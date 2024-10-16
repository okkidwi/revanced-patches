package app.revanced.patches.youtube.utils.lottie

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.youtube.utils.integrations.Constants.UTILS_PATH
import app.revanced.patches.youtube.utils.lottie.fingerprints.SetAnimationFingerprint
import app.revanced.patches.youtube.utils.lottie.fingerprints.SetAnimationFingerprint.LOTTIE_ANIMATION_VIEW_CLASS_DESCRIPTOR
import app.revanced.util.findMethodOrThrow
import app.revanced.util.resultOrThrow

@Patch(
    description = "Hook YouTube to use LottieAnimationView.setAnimation in the integration.",
)
object LottieAnimationViewHookPatch : BytecodePatch(
    setOf(SetAnimationFingerprint)
) {
    private const val INTEGRATIONS_CLASS_DESCRIPTOR =
        "$UTILS_PATH/LottieAnimationViewPatch;"

    override fun execute(context: BytecodeContext) {
        context.findMethodOrThrow(INTEGRATIONS_CLASS_DESCRIPTOR) {
            name == "setAnimation"
        }.addInstruction(
            0,
            "invoke-virtual {p0, p1}, " +
                    LOTTIE_ANIMATION_VIEW_CLASS_DESCRIPTOR +
                    "->" +
                    SetAnimationFingerprint.resultOrThrow().mutableMethod.name +
                    "(I)V"
        )
    }
}
package app.revanced.extension.music.patches.utils;

import androidx.annotation.Nullable;

import app.revanced.extension.music.shared.VideoType;

@SuppressWarnings("unused")
public class VideoTypeHookPatch {
    /**
     * Injection point.
     */
    public static void setVideoType(@Nullable Enum<?> musicVideoType) {
        if (musicVideoType == null)
            return;

        VideoType.setFromString(musicVideoType.name());
    }
}


package cs121.picture;

import java.util.function.Function;

@SuppressWarnings("unused")
public abstract class PictureTransformer implements Function<Picture, Picture> {

	@Override
	public abstract Picture apply(Picture picture);

	public static PictureTransformer compose2(PictureTransformer transformer2, PictureTransformer transformer1) {
		return new PictureTransformer() {
			public Picture apply(Picture picture) {
				return picture.transform(transformer1).transform(transformer2);
			}
		};
	}

	public static PictureTransformer vaCompose(PictureTransformer... transformers) {
		return new PictureTransformer() {
			public Picture apply(Picture oldPicture) {
				int len = transformers.length;
				for (int index = len - 1; index >= 0; index--) {
					oldPicture = oldPicture.transform(transformers[index]);
				}
				return oldPicture;
			}
		};
	}
}

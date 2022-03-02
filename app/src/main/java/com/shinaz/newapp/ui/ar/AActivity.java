package com.shinaz.newapp.ui.ar;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Pose;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.PlaneRenderer;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.shinaz.newapp.R;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class AActivity extends AppCompatActivity {
    private static final String TAG = AActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;


    ArFragment arFragment;
    ModelRenderable lampPostRenderable;
    ViewRenderable viewRenderable;
    private ImageView imgView;
    private ModelRenderable redSphereRenderable;

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }
        setContentView(R.layout.fragment_a_r_put);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        String GLTF_ASSET = "https://github.com/KhronosGroup/glTF-Sample-Models/raw/master/2.0/Duck/glTF/Duck.gltf";

//        ModelRenderable.builder()
//                .setSource(this, Uri.parse("model_fight.sfb"))
//                .build()
//                .thenAccept(renderable -> lampPostRenderable = renderable)
//                .exceptionally(throwable -> {
//                    Toast toast = Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                    toast.show();
//                    return null;
//                });


//        ViewRenderable.builder()
//                .setView(this, R.layout.img_board)
//                .build()
//                .thenAccept(renderable -> {
////                    imgView = (ImageView)renderable.getView();
//
//                    viewRenderable = renderable;
//
//                })
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load renderable " +
//                                            GLTF_ASSET, Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });
        ;

        MaterialFactory.makeOpaqueWithColor(this, new Color(android.graphics.Color.RED))
                .thenAccept(
                        material -> {
                            redSphereRenderable =
                                    ShapeFactory.makeSphere(0.01f, new Vector3(0.0f, 0.15f, 0.0f), material);
                        });


        /* When you build a Renderable, Sceneform loads model and related resources
         * in the background while returning a CompletableFuture.
         * Call thenAccept(), handle(), or check isDone() before calling get().
         */
//        ModelRenderable.builder()
//                .setSource(this, RenderableSource.builder().setSource(
//                        this,
//                        Uri.parse("scene.gltf"),
//                        RenderableSource.SourceType.GLTF2)
//                        .setScale(1f)  // Scale the original model to 50%.
//                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
//                        .build())
//                .setRegistryId("scene")
//                .build()
//                .thenAccept(renderable ->
//                        lampPostRenderable = renderable
//                )
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load renderable " +
//                                            GLTF_ASSET, Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });


        arFragment.setOnTapArPlaneListener(
                (HitResult hitresult, Plane plane, MotionEvent motionevent) -> {
//                    if (lampPostRenderable == null){
//                        return;
//                    }

//                    Log.e("event", String.valueOf(motionevent.getAction()));
//                    Log.e("event", String.valueOf(motionevent.getRawX()));
                    Anchor anchor = hitresult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    TransformableNode lamp = new TransformableNode(arFragment.getTransformationSystem());
                    lamp.setParent(anchorNode);
                    lamp.setRenderable(redSphereRenderable);
                    lamp.select();


                }
        );

        arFragment.setOnTapArPlaneListener(
                (HitResult hitresult, Plane plane, MotionEvent motionevent) -> {
//                    if (lampPostRenderable == null){
//                        return;
//                    }
                    Log.e("event", String.valueOf(motionevent.getAction()));
                    Log.e("event", String.valueOf(motionevent.getRawX()));
                    ArrayList<Anchor> anchorArr = new ArrayList<>();
                    anchorArr.add(0, hitresult.createAnchor());
                    AnchorNode anchorNode = new AnchorNode(anchorArr.get(0));
                    anchorNode.setParent(arFragment.getArSceneView().getScene());



//                    Pose pos = frame.getCamera().getPose().compose(Pose.makeTranslation(0, 0, -0.3f));

                    TransformableNode lamp = new TransformableNode(arFragment.getTransformationSystem());
                    lamp.setParent(anchorNode);
                    lamp.setRenderable(viewRenderable);
                    lamp.select();

                    for(int i = 0;i<=10;i++){
                        Pose pos = anchorArr.get(i).getPose().compose(Pose.makeTranslation(0.4f, 0, 0));
                        anchorArr.add(i + 1, Objects.requireNonNull(arFragment.getArSceneView().getSession()).createAnchor(pos));

                        AnchorNode anchorNode1 = new AnchorNode(anchorArr.get(i + 1));
                        anchorNode1.setParent(arFragment.getArSceneView().getScene());


                        TransformableNode lamp1 = new TransformableNode(arFragment.getTransformationSystem());
                        lamp1.setParent(anchorNode1);
                        lamp1.setRenderable(viewRenderable);
//                        lamp1.select();
                    }

                }
        );
//        CompletableFuture<Texture> build = Texture.builder().setSource(this, R.drawable.ic_launcher_foreground).build();

//        arFragment.getArSceneView()
//                .getPlaneRenderer()
//                .getMaterial()
//                .thenAcceptBoth(build, (material, texture) -> {
//                    material.setTexture(PlaneRenderer.MATERIAL_TEXTURE, texture);
//                    material.setFloat2(PlaneRenderer.MATERIAL_UV_SCALE, 50.0f, 50.0f);
//                    material.setFloat3(PlaneRenderer.MATERIAL_COLOR, new Color(0.0f, 0.0f, 1.0f, 1.0f));
//                });

    }
}
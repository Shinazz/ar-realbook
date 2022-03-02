package com.shinaz.newapp.ui.ar.ui.after

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.ar.core.*
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.*
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.shinaz.newapp.R
import com.shinaz.newapp.databinding.FragmentARPutBinding
import com.shinaz.newapp.ui.ar.ArActivity
import com.shinaz.newapp.ui.ar.ui.ArViewModel
import com.shinaz.newapp.ui.base.BaseFragment
import java.lang.Math.abs
import java.util.*
import javax.inject.Inject


/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */


fun Double.equalsDelta(other: Double) = abs(this / other - 1) < 0.00001
class ARPutFragment: BaseFragment<FragmentARPutBinding, ArViewModel>(){
    @Inject
    lateinit var arViewModel: ArViewModel

    private val TAG: String = ARPutFragment::class.java.getSimpleName()
    private val MIN_OPENGL_VERSION = 3.0
    var arFragment: ArFragment? = null
    var lampPostRenderable: ModelRenderable? = null
    var viewRenderable: ViewRenderable? = null
    private val imgView: ImageView? = null
    private var redSphereRenderable: ModelRenderable? = null

    fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later")
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG)
                .show()
            activity.finish()
            return false
        }
        val openGlVersionString =
            (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                .deviceConfigurationInfo
                .glEsVersion
        if (openGlVersionString.toDouble() < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later")
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                .show()
            activity.finish()
            return false
        }
        return true
    }

    override val layoutId: Int
        get() = R.layout.fragment_a_r_put

    override val viewModel: ArViewModel
        get() = arViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!checkIsSupportedDeviceOrFinish(requireActivity())) {
            return
        }

        checkForPins()
        arFragment = childFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment?
        val GLTF_ASSET =
            "https://github.com/KhronosGroup/glTF-Sample-Models/raw/master/2.0/Duck/glTF/Duck.gltf"

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
        ViewRenderable.builder()
            .setView(requireActivity(), R.layout.img_board)
            .build()
            .thenAccept { renderable ->
//                    imgView = (ImageView)renderable.getView();
                val  imgView = renderable.getView().findViewById(R.id.imageCard) as ImageView
                val  title = renderable.getView().findViewById(R.id.titleAR) as TextView
               imgView.setImageBitmap((requireActivity() as ArActivity).bitmapfromDB)
                title.setText("this is DYNAMIC!!!")
               viewRenderable = renderable
            }
            .exceptionally { throwable ->
                val toast: Toast = Toast.makeText(
                        requireActivity(), "Unable to load renderable " +
                        GLTF_ASSET, Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                null
            }
        MaterialFactory.makeOpaqueWithColor(requireActivity(), Color(android.graphics.Color.RED))
            .thenAccept { material ->
                redSphereRenderable =
                    ShapeFactory.makeSphere(0.01f, Vector3(0.0f, 0.15f, 0.0f), material)
            }


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
        arFragment?.setOnTapArPlaneListener { hitresult: HitResult, plane: Plane?, motionevent: MotionEvent? ->
//                    if (lampPostRenderable == null){
//                        return;
//                    }

//                    Log.e("event", String.valueOf(motionevent.getAction()));
//                    Log.e("event", String.valueOf(motionevent.getRawX()));
            val anchor: Anchor = hitresult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment!!.getArSceneView().getScene())
            val lamp = TransformableNode(arFragment!!.getTransformationSystem())
            lamp.setParent(anchorNode)
            lamp.setRenderable(redSphereRenderable)
            lamp.select()
        }
        arFragment?.setOnTapArPlaneListener { hitresult: HitResult, plane: Plane?, motionevent: MotionEvent ->
//                    if (lampPostRenderable == null){
//                        return;
//                    }
            Log.e("event", motionevent.action.toString())
            Log.e("event", motionevent.rawX.toString())
            val anchorArr: ArrayList<Anchor> = ArrayList<Anchor>()
            anchorArr.add(0, hitresult.createAnchor())
            val anchorNode = AnchorNode(anchorArr[0])
            anchorNode.setParent(arFragment?.getArSceneView()?.getScene())


//                    Pose pos = frame.getCamera().getPose().compose(Pose.makeTranslation(0, 0, -0.3f));
            val lamp = TransformableNode(arFragment?.getTransformationSystem())
            lamp.setParent(anchorNode)
            lamp.setRenderable(viewRenderable)
            lamp.select()
//            for (i in 0..10) {
//                val pos: Pose = anchorArr[i].getPose().compose(Pose.makeTranslation(0.4f, 0f, 0f))
//                Objects.requireNonNull(arFragment?.getArSceneView()?.getSession())?.let {
//                    anchorArr.add(
//                        i + 1,
//                        it
//                            .createAnchor(pos)
//                    )
//                }
//                val anchorNode1 = AnchorNode(anchorArr[i + 1])
//                anchorNode1.setParent(arFragment?.getArSceneView()?.getScene())
//                val lamp1 =
//                    TransformableNode(arFragment?.getTransformationSystem())
//                lamp1.setParent(anchorNode1)
//                lamp1.setRenderable(viewRenderable)
//                //                        lamp1.select();
//            }
//            lamp.setRenderable(viewRenderable)

        }
//        val build: CompletableFuture<Texture> =
//            Texture.builder().setSource(requireActivity(), R.drawable.ic_launcher_foreground).build()
//        arFragment?.getArSceneView()
//            ?.getPlaneRenderer()
//            ?.getMaterial()
//            ?.thenAcceptBoth(build) { material, texture ->
//                material.setTexture(PlaneRenderer.MATERIAL_TEXTURE, texture)
//                material.setFloat2(PlaneRenderer.MATERIAL_UV_SCALE, 50.0f, 50.0f)
//                material.setFloat3(PlaneRenderer.MATERIAL_COLOR, Color(0.0f, 0.0f, 1.0f, 1.0f))
//            }


        arFragment?.getArSceneView()?.getScene()?.addOnUpdateListener(this::onUpdateFrame);
    }

    var placed = false
    private fun onUpdateFrame(frameTime: FrameTime) {
        val frame = arFragment!!.arSceneView.arFrame ?: return

        // If there is no frame, just return.

        //Making sure ARCore is tracking some feature points, makes the augmentation little stable.
        if (frame.camera.trackingState === TrackingState.TRACKING && !placed) {
            val pos = frame.camera.pose.compose(Pose.makeTranslation(0f, 0f, -5f)).compose(Pose.makeRotation(0f,0f,-10f,0f))
            val anchor = arFragment!!.arSceneView.session!!.createAnchor(pos)
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment!!.arSceneView.scene)

            // Create the arrow node and add it to the anchor.
            val arrow = TransformableNode(arFragment?.getTransformationSystem())

            arrow.setParent(anchorNode)
            arrow.setRenderable(viewRenderable)
            placed = true //to place the arrow just once.
        }
    }

    private fun checkForPins() {
        (requireActivity() as ArActivity).itemLocation?.latitude?.let {
            if(
            (requireActivity() as ArActivity).locFromDB.latitude.equalsDelta(it)
            ) {

            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun newInstance() = ARPutFragment()

    }
}


//public void onSensorChanged(SensorEvent event) {
//    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
//        mGravity = event.values;
//    if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
//        mGeomagnetic = event.values;
//    if (mGravity != null && mGeomagnetic != null) {
//        float R [] = new float [9];
//        float outR [] = new float [9];
//        float I [] = new float [9];
//
//        boolean success = SensorManager . getRotationMatrix (R, I, mGravity, mGeomagnetic);
//        if (success) {
//            float orientation [] = new float [3];
//
//            SensorManager.remapCoordinateSystem(R, SensorManager.AXIS_X, SensorManager.AXIS_Y, outR);
//
//            SensorManager.getOrientation(outR, orientation);
//            azimut = orientation[0];
//
//            float degree =(float)(Math.toDegrees(azimut) + 360) % 360;
//
//            System.out.println("degree " + degree);
//        }
//    }
//}
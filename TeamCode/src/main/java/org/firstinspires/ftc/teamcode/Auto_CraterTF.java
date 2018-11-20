package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import java.lang.Math;


@Autonomous(name="Auto_CraterTF", group="Pushbot")

public class Auto_CraterTF extends LinearOpMode {

    //variables
    BasicAutoHardwareMap1819 robot = new BasicAutoHardwareMap1819();
    private ElapsedTime runtime = new ElapsedTime();

    private String Order = null;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "AceHAhf/////AAAAGXcP1HmPtEkhmi5YyP3W2S1gVnAvF2sEhNkTVsdCy4iDjm5aVrODZUpSZDIQZXVqOIqmjWvWcv1+56gq4NJ8h9P0m/MlKuqKbjcQNbSrxfQoBCJbD1G9gmkKFeaeKCrV/8ZQsipnso84dJHek4OfzMdvtKUU/QDrk+YCE7SWGMtZr7kFAWYss3vTpGv0WynOurUd+rly24nTP4qERK311b9MkK+uliO/slCL/vg6vANVX/NGSlXLRe4/nK0HitcsLrLjvcuRQJGeaYnzFB/ykuSZw3hFbHaSP45KH/fLivm0fql8ENaPyCLiNSDiqlSH553rXNiRenz3R9t8TW5YJjjAThy1U0F7GHtkGXKN/pfL";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    @Override

    public void runOpMode() {
        robot.init(hardwareMap);
        initVuforia();
        initTfod();
        waitForStart();
        //linearExt(1, 14);
        //unlatch();
        //linearRet(1, 14);
        //tensorActivate();
        //tensorFlow();
        //tensorDeactivate();
        //Sample();
        //goToDepot();
        //regurgitate();
        //goToCrater();
    }

    public void moveForward(double powerL, double powerR, long time) {
        robot.motorBL.setPower(-powerL);
        robot.motorBR.setPower(powerR);
        sleep(time);
        robot.motorBL.setPower(0);
        robot.motorBR.setPower(0);
        sleep(500);
    }

    public void moveForwardEn(double powerL, double powerR, float distL, float distR) {
        robot.motorBR.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.motorBL.setDirection(DcMotorSimple.Direction.REVERSE);

        int tickL = (int) cm_conv(distL);
        int tickR = (int) cm_conv(distR);

        robot.motorBL.setTargetPosition(tickL);
        robot.motorBR.setTargetPosition(tickR);

        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        robot.motorBL.setPower(powerL);
        robot.motorBR.setPower(powerR);

        robot.motorBR.setPower(0);
        robot.motorBL.setPower(0);

        robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void moveBackward(double powerL, double powerR, long time) {
        robot.motorBL.setPower(powerL);
        robot.motorBR.setPower(-powerR);
        sleep(time);
        robot.motorBL.setPower(0);
        robot.motorBR.setPower(0);
        sleep(500);
    }

    public void moveBackwardEn(double powerL, double powerR, float distL, float distR) {
        robot.motorBR.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.motorBL.setDirection(DcMotorSimple.Direction.FORWARD);

        int tickL = (int) cm_conv(distL);
        int tickR = (int) cm_conv(distR);

        robot.motorBL.setTargetPosition(tickL);
        robot.motorBR.setTargetPosition(tickR);

        robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        robot.motorBL.setPower(powerL);
        robot.motorBR.setPower(powerR);

        robot.motorBR.setPower(0);
        robot.motorBL.setPower(0);

        robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void Sample() {

        //rotate to face forward



        if (Order.equals("LEFT")) {
            //go left
        } else if (Order.equals("RIGHT")) {
            //go right
        } else if (Order.equals("CENTER")) {
            //go forward
        }
    }

    public void goToDepot() {
        //rotate to left


        //drive forward


        //rotate to face depot


        //drive forward

    }

    public void regurgitate() {
        robot.hopperB.setPower(1);
        robot.hopperT.setPower(1);
        sleep(1000);
        robot.hopperB.setPower(0);
        robot.hopperT.setPower(0);
    }

    public void goToCrater() {
        moveBackward(1, 1, 3000);
    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    private void tensorActivate() {
        if (tfod != null) {
            tfod.activate();
        }
    }

    private void tensorFlow() {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() == 3) {
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }
                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Left");
                            Order = "LEFT";
                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Right");
                            Order = "RIGHT";
                        } else {
                            telemetry.addData("Gold Mineral Position", "Center");
                            Order = "Center";
                        }
                    }
                }
                telemetry.update();
            }
        }
    }

    private void tensorDeactivate() {
        if (tfod != null) {
            tfod.shutdown();
        }
    }

    public double cm_conv(float dist) {
        return(Math.round(280 * dist / (3.1415 * 10)));
    }

    public double rot_conv(float rot) {
        return (Math.round(280 * rot));
    }

    private void linearExt(double power, float rot) {
        robot.motorBR.setDirection(DcMotorSimple.Direction.FORWARD);

        int tick = (int) rot_conv(rot);

        robot.linearA.setTargetPosition(tick);

        robot.linearA.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        robot.linearA.setPower(power);

        robot.linearA.setPower(0);

        robot.linearA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.linearA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void linearRet(double power, float rot) {
        robot.motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

        int tick = (int) rot_conv(rot);

        robot.linearA.setTargetPosition(tick);

        robot.linearA.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        robot.linearA.setPower(power);

        robot.linearA.setPower(0);

        robot.linearA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.linearA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void unlatch() {

    }
}
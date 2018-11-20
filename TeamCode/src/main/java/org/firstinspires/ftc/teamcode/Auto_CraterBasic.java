package org.firstinspires.ftc.teamcode;
//package org.firstinspires.ftc.teamcode.dogecv;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldDetector;
import com.disnodeteam.dogecv.filters.HSVColorFilter;


@Autonomous(name="Auto_CraterBasic", group="Pushbot")

public class Auto_CraterBasic extends LinearOpMode {

    //variables
    BasicHardwareMap1819 robot = new BasicHardwareMap1819();
    private ElapsedTime runtime = new ElapsedTime();

    private SamplingOrderDetector detector;

    private String Order = null;

    @Override

    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        Sample();
        goToDepot();
        regurgitate();
        goToCrater();
        disableDetector();
    }

    public void moveForward(double powerL, double powerR, long time) {
        robot.motorBL.setPower(-powerL);
        robot.motorBR.setPower(powerR);
        sleep(time);
        robot.motorBL.setPower(0);
        robot.motorBR.setPower(0);
        sleep(500);
    }

    public void moveBackward(double powerL, double powerR, long time) {
        robot.motorBL.setPower(powerL);
        robot.motorBR.setPower(-powerR);
        sleep(time);
        robot.motorBL.setPower(0);
        robot.motorBR.setPower(0);
        sleep(500);
    }

    public void getSampleOrder() {
        telemetry.addData("Status", "DogeCV 2018.0 - Sampling Order Example");

        // Setup detector
        detector = new SamplingOrderDetector(); // Create the detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize detector with app context and camera
        detector.useDefaults(); // Set detector to use default settings

        detector.downscale = 0.4; // How much to downscale the input frames

        // Optional tuning
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.001;

        detector.ratioScorer.weight = 15;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable(); // Start detector
    }

    public void disableDetector() {
        detector.disable();
    }

    public void Sample() {
        sleep(1000);
        getSampleOrder();
        Order = detector.getCurrentOrder().toString();

        sleep(1000);

        /*while (opModeIsActive()) {
            telemetry.addData("Current Order" , Order);
        }*/

        //rotate to face forward
        moveForward(-1, 1, 600);


        if (Order.equals("LEFT")) {
            //go left
            telemetry.addData("Direction", "Left");
            moveBackward(-1, 1, 200);
        } else if (Order.equals("RIGHT")) {
            telemetry.addData("Direction", "Right");
            //go right
        } else if (Order.equals("CENTER")) {
            telemetry.addData("Direction", "Center");
            //go forward
            moveForward(0.2, 0.2, 200);
            moveBackward(0.2, 0.2, 200  );
        }
    }

    public void goToDepot() {
        //rotate to left
        moveForward(-1, 1, 700);

        //drive forward
        moveForward(1, 1, 1500);

        //rotate to face depot
        moveForward(-1, 1, 600);

        //drive forward
        moveForward(1, 1, 2000);
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
}

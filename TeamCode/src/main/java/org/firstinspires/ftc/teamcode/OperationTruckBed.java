package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="OperationTruckBed", group="Test")

public class OperationTruckBed extends LinearOpMode {

    //Connect Hardware Maps
    OperationTruckBedHardware robot = new OperationTruckBedHardware();

    //Run LinearOpMode
    @Override
    public void runOpMode() throws InterruptedException {

        //Initiate Hardware Map
        robot.init(hardwareMap);

        //Add Telemetry to confirm Robot Start. Recommended to name her here
        telemetry.addData("Say", "CandyPault ready to FIRE");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //check for strafing then define X and Y values for standard drive
            if (gamepad1.right_trigger > 0){
                robot.motorP.setPower(0.2);
            }
            else if (gamepad1.left_trigger > 0){
                robot.motorP.setPower(-0.2);
            }
            else {
                robot.motorP.setPower(0);
            }

            if (gamepad1.a) {
                robot.motorC.setPower(1);
                sleep(750);
                robot.motorC.setPower(0);
            }
        }
    }
}
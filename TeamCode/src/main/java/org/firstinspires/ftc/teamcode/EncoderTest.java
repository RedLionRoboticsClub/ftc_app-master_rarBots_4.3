package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="EncoderTest", group="Test")

public class EncoderTest extends LinearOpMode {

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

        robot.motorP.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorC.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            robot.motorP.setPower(0.25);
            int position = robot.motorP.getCurrentPosition();
            telemetry.addData("Encoder Position", position);
        }
    }
}

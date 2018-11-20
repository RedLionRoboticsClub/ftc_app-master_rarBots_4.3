package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class OperationTruckBedHardware {

    //Define Motors/Servos
    public DcMotor motorP = null;
    public DcMotor motorC = null;

    // Local OpMode members
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    // Constructor
    public OperationTruckBedHardware() {
    }

    //Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors/Servos
        motorP = hwMap.get(DcMotor.class, "motorPivot");
        motorC = hwMap.get(DcMotor.class, "motorCatapault");

        // Set all motors to zero power
        motorP.setPower(0);
        motorC.setPower(0);

        // Set all motors to run using or without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        motorP.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorC.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos
    }
}

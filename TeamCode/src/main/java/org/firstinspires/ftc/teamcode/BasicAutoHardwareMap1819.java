package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BasicAutoHardwareMap1819 {

    //Define Motors/Servos
    //public DcMotor  motorFL   = null;
    //public DcMotor  motorFR   = null;
    public DcMotor  motorBL   = null;
    public DcMotor  motorBR   = null;
    public DcMotor  hopperT   = null;
    public DcMotor  hopperB   = null;
    public DcMotor  linearA   = null;
    public Servo    box       = null;

    public final static double box_home = 0.65;

    // Local OpMode members
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    // Constructor
    public BasicAutoHardwareMap1819() {
    }

    //Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors/Servos
        //motorFL  = hwMap.get(DcMotor.class, "motorFL");
        //motorFR = hwMap.get(DcMotor.class, "motorFR");
        motorBL = hwMap.get(DcMotor.class, "motorBL");
        motorBR = hwMap.get(DcMotor.class, "motorBR");
        hopperT = hwMap.get(DcMotor.class, "hopperT");
        hopperB = hwMap.get(DcMotor.class, "hopperB");
        linearA = hwMap.get(DcMotor.class, "linearA");


        // Set all motors to zero power

        //motorFL.setPower(0);
        //motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
        hopperT.setPower(0);
        hopperB.setPower(0);
        linearA.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.

        //motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hopperT.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hopperB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define and initialize ALL installed servos
        box     = hwMap.get(Servo.class, "box");
        box.setPosition(box_home);
    }
}
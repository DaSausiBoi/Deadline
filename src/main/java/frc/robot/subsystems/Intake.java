package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.CanIdConstants;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import static frc.robot.Constants.*;

public class Intake extends SubsystemBase{
    private CANSparkMax intakeLeader = new CANSparkMax(CanIdConstants.INTAKE_LEADER_ID, MotorType.kBrushed); // change when using 550
    private CANSparkMax intakeFollower = new CANSparkMax(CanIdConstants.INTAKE_FOLLOWER_ID, MotorType.kBrushed);

    /** Creates a new Intake Subsystem*/
    public Intake(){
        intakeLeader.setOpenLoopRampRate(0.7);
        intakeLeader.setIdleMode(IdleMode.kBrake);
        intakeLeader.clearFaults();

        intakeFollower.setOpenLoopRampRate(0.7);
        intakeFollower.setIdleMode(IdleMode.kBrake);
        intakeFollower.clearFaults();
        intakeFollower.setInverted(true);

        intakeFollower.follow(intakeLeader);
    }

    public void setIntakeVelo(double velo){
        //command below needs fixing
        intakeLeader.set(velo);
    }
}
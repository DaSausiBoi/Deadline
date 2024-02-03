package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.CanIdConstants;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import static frc.robot.Constants.*;

public class Arm extends SubsystemBase{
    private CANSparkMax armLeader = new CANSparkMax(CanIdConstants.ARM_LEADER_ID, MotorType.kBrushed); // change when using 550
    private CANSparkMax armFollower = new CANSparkMax(CanIdConstants.ARM_FOLLOWER_ID, MotorType.kBrushed);

    /** Creates a new Arm Subsystem*/
    public Arm(){
        armLeader.setOpenLoopRampRate(0.7);
        armLeader.setIdleMode(IdleMode.kBrake);
        armLeader.clearFaults();

        armFollower.setOpenLoopRampRate(0.7);
        armFollower.setIdleMode(IdleMode.kBrake);
        armFollower.clearFaults();
        armFollower.setInverted(true);

        armFollower.follow(armLeader);
    }

    public void setArmVelo(double velo){
        //command below needs fixing
        armLeader.set(velo);
    }
}
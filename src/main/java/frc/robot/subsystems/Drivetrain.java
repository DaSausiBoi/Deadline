package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CanIdConstants;

public class Drivetrain extends SubsystemBase{
    private CANSparkMax rightLeader = new CANSparkMax(CanIdConstants.RIGHT_LEADER_ID, MotorType.kBrushless);
    private CANSparkMax rightFollower = new CANSparkMax(CanIdConstants.RIGHT_FOLLOWER_ID, MotorType.kBrushless);
    private CANSparkMax leftLeader = new CANSparkMax(CanIdConstants.LEFT_LEADER_ID, MotorType.kBrushless);
    private CANSparkMax leftFollower = new CANSparkMax(CanIdConstants.LEFT_FOLLOWER_ID, MotorType.kBrushless);
    private final PowerDistribution pdh = new PowerDistribution();

    private final DifferentialDrive differentialDrive = new DifferentialDrive(leftLeader, rightLeader);

    public Drivetrain() {
        pdh.clearStickyFaults();

        rightLeader.clearFaults();
        rightFollower.clearFaults();
        leftLeader.clearFaults();
        leftFollower.clearFaults();

        rightLeader.setInverted(true);
        rightFollower.setInverted(true);

        rightLeader.setOpenLoopRampRate(0.5);
        rightFollower.setOpenLoopRampRate(0.5);
        leftLeader.setOpenLoopRampRate(0.5);
        leftFollower.setOpenLoopRampRate(0.5);

        rightLeader.setSmartCurrentLimit(40);
        rightFollower.setSmartCurrentLimit(40);
        leftLeader.setSmartCurrentLimit(40);
        leftFollower.setSmartCurrentLimit(40);

        rightLeader.setIdleMode(IdleMode.kBrake);
        rightFollower.setIdleMode(IdleMode.kBrake);
        leftLeader.setIdleMode(IdleMode.kBrake);
        leftFollower.setIdleMode(IdleMode.kBrake);

        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);

        rightLeader.burnFlash();
        rightFollower.burnFlash();
        leftLeader.burnFlash();
        leftFollower.burnFlash();
    }

    @Override
    public void periodic() {
      SmartDashboard.putNumber("Battery Voltage", pdh.getVoltage());
      SmartDashboard.putNumber("Left Amps", leftLeader.getOutputCurrent());
      SmartDashboard.putNumber("Right Amps", rightLeader.getOutputCurrent());
    }

  public void setRampRate(double rate) {
    leftLeader.setOpenLoopRampRate(rate);
    leftFollower.setOpenLoopRampRate(rate);
    rightLeader.setOpenLoopRampRate(rate);
    rightFollower.setOpenLoopRampRate(rate);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    differentialDrive.arcadeDrive(fwd, rot);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftLeader.setVoltage(leftVolts);
    rightLeader.setVoltage(rightVolts);
    differentialDrive.feed();
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    differentialDrive.setMaxOutput(maxOutput);
  }
}

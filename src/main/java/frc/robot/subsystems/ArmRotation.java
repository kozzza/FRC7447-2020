/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.RobotMap;
import frc.robot.commands.ArmRotationCommand;

/**
 * Add your docs here.
 */
public class ArmRotation extends Subsystem {
  public WPI_VictorSPX rightArmMotor = new WPI_VictorSPX(RobotMap.rightArmMotor);
  public WPI_VictorSPX leftArmMotor = new WPI_VictorSPX(RobotMap.leftArmMotor);

  public void turn(double percentVoltage) {
    rightArmMotor.set(ControlMode.PercentOutput, percentVoltage);
    leftArmMotor.set(ControlMode.PercentOutput, percentVoltage);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArmRotationCommand());
  }
}

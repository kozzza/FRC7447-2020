/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class WinchSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_VictorSPX rightWinch = new WPI_VictorSPX(RobotMap.rightWinchPort);
  private WPI_VictorSPX leftWinch = new WPI_VictorSPX(RobotMap.leftWinchPort);
  private WPI_VictorSPX teleArm = new WPI_VictorSPX(RobotMap.teleArmPort);

  public WinchSystem() {
    leftWinch.follow(rightWinch);
  }

  public void winchSystem (double percentVoltage) {
    rightWinch.set(ControlMode.PercentOutput, percentVoltage);
  }

  public void teleArmRaise (double percentVoltage) {
    teleArm.set(ControlMode.PercentOutput, percentVoltage);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

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
import frc.robot.commands.IntakeCommand;

/**
 * Add your docs here.
 */
public class IntakeSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  //instantiate new motor control objects

  public WPI_VictorSPX intakeMaster = new WPI_VictorSPX (RobotMap.intakeMasterPort);
  public WPI_VictorSPX intakeSlave = new WPI_VictorSPX (RobotMap.intakeSlavePort);


  public void IntakeSubsystem() {
    intakeSlave.follow(intakeMaster);
  }

  //instantiate a new DifferentialDrive object


  public void intakeSpin(double percentVoltage) {
    intakeMaster.set(ControlMode.PercentOutput, percentVoltage);
    intakeSlave.set(ControlMode.PercentOutput, percentVoltage);
  }

  //create constructor function
  
  //add manualDrive() method



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new IntakeCommand());
  }



}
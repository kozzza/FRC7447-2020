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
public class IntakeSubsystem extends Subsystem {
  
  //instantiate new motor control objects

  public WPI_VictorSPX intakeMaster = new WPI_VictorSPX(RobotMap.intakeMasterPort);
  public WPI_VictorSPX intakeSlave = new WPI_VictorSPX(RobotMap.intakeSlavePort);


  // public IntakeSubsystem() {
  //   intakeSlave.follow(intakeMaster);
  // }


  public void intakeSpin(double percentVoltage, double powerFactor) {
    intakeMaster.set(ControlMode.PercentOutput, percentVoltage);
    intakeSlave.set(ControlMode.PercentOutput, (percentVoltage * powerFactor));
    
  }



  @Override
  public void initDefaultCommand() {
  }

}
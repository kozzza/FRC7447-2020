/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManuallyCommand;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
   private WPI_VictorSPX leftMaster = new WPI_VictorSPX(RobotMap.leftMaster);
   private WPI_VictorSPX leftSlave0 = new WPI_VictorSPX(RobotMap.leftSlave0);
   private WPI_VictorSPX leftSlave1 = new WPI_VictorSPX(RobotMap.leftSlave1);
   private WPI_VictorSPX rightMaster = new WPI_VictorSPX(RobotMap.rightMaster);
   private WPI_VictorSPX rightSlave0 = new WPI_VictorSPX(RobotMap.rightSlave0);
   private WPI_VictorSPX rightSlave1 = new WPI_VictorSPX(RobotMap.rightSlave1);


  // Put methods for controlling this subsystem
  // here. Call these from Commands.

   // differentialdrive object
   public DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

   public DriveTrain() {
      leftSlave0.follow(leftMaster);
      leftSlave1.follow(leftMaster);
      rightSlave0.follow(rightMaster);
      rightSlave1.follow(rightMaster);
   }
   // driving with joy stick
   // move is y-axis (up and down) and turn is x-axis (left and right)
   public void manualDrive(double move, double turn) {
     // passing arguments move and turn into drive function
     drive.arcadeDrive(move, turn);
   }
 
   @Override
   public void initDefaultCommand() {
     // Set the default command for a subsystem here.
     setDefaultCommand(new DriveManuallyCommand());
   }
}

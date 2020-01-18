/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleConsumer;

/** Wraps the wpilib2 PIDCommand without the vararg call
*/
public class PIDCommandWrapper extends PIDCommand {
  /**
   * Creates a new PIDCommandWrapper.
   */
  public PIDCommandWrapper(PIDController pidController, DoubleSupplier p1, DoubleSupplier p2, DoubleConsumer p3) {
    super(pidController, p1, p2, p3);
  }
}

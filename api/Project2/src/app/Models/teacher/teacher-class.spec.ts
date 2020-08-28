import { TeacherClass } from './teacher-class';
import { TeacherAssignment } from './teacher-assignment';
import { Grade } from './grade';


describe('TeacherClass', () => {
  it('should create an instance', () => {
    let grade = new Grade(1001, 'testFirst', 'testLast', 85)
    let teacherAssignment = new TeacherAssignment([grade], '08/26/2020', 100, 'Assignment1', 'Assignment')
    expect(new TeacherClass(80, 80, 'History', 80, [teacherAssignment], 
    80, 80, 80, 'History101', 80, 80, 80, 'Teacher', 1002 )).toBeTruthy();
  });
});

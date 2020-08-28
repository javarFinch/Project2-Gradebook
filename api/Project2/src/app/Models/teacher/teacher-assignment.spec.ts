import { TeacherAssignment } from './teacher-assignment';
import { Grade } from './grade';

describe('TeacherAssignment', () => {
  it('should create an instance', () => {
    let gradeList = new Grade(1001, 'testFirst', 'testLast', 85)
    expect(new TeacherAssignment([gradeList],'08/26/2020', 100, 'Assignment1', 'Assignment')).toBeTruthy();
  });
});

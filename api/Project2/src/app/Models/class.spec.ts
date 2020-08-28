import { Class } from './class';

describe('Class', () => {
  it('should create an instance', () => {
    expect(new Class(20, 14, 'History', 80, 70,
      [{assignmentName: 'Assignment1', assignmentType:'Assignment', dueDate: '08/26/2020',
      totalPoints: 100, actualPoints: 95}],
      60, 80, 36, 'History101', 28, 83, 'Teacher'))
      .toBeTruthy();
  });
});

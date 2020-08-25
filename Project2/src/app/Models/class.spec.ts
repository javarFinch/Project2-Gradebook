import { Class } from './class';

describe('Class', () => {
  it('should create an instance', () => {
    expect(new Class(20, 14, 'History', 80, 70,
      [{AssignmentName: 'Assignment1', AssignmentType:'Assignment', DueDate: '08/26/2020',
      TotalPoints: 100, ActualPoints: 95}],
      60, 80, 36, 'History101', 28, 83, 'Teacher'))
      .toBeTruthy();
  });
});

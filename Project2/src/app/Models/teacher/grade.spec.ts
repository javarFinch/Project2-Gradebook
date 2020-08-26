import { Grade } from './grade';

describe('Grade', () => {
  it('should create an instance', () => {
    expect(new Grade(1001, 'testFirst', 'testLast', 85)).toBeTruthy();
  });
});

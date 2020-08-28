import { Assignment } from './assignment';

export class Class {

    constructor(
      public quizWeight: number,
      public homeworkGrade: number,
      public classSubject: string,
      public testGrade: number,
      public testWeight: number,
      public assignmentList: Assignment[],
      public quizGrade: number,
      public overAllGrade: number,
      public participationGrade: number,
      public className: string,
      public participationWeight: number,
      public homeworkWeight: number,
      public teacherName: string
      ) {}
}

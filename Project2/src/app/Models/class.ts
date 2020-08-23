import { Assignment } from './assignment';

export class Class {

    constructor(
      public QuizWeight: number,
      public HomeworkGrade: number,
      public ClassSubject: string,
      public TestGrade: number,
      public TestWeight: number,
      public AssignmentList: Assignment[],
      public QuizGrade: number,
      public OverAllGrade: number,
      public ParticipationGrade: number,
      public ClassName: string,
      public ParticipationWeight: number,
      public HomeworkWeight: number,
      public TeacherName: string
      ) {}
}

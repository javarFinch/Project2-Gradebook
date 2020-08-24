import { TeacherAssignment } from './teacher-assignment';
export class TeacherClass {
    constructor(
        public QuizWeight: number,
        public ParticipationAverage: number,
        public ClassSubject: string,
        public TestWeight: number,
        public AssignmentList: TeacherAssignment[],
        public QuizAverage: number,
        public OverAllAverage: number,
        public TestAverage: number,
        public ClassName: string,
        public HomeworkAverage: number,
        public ParticipationWeight: number,
        public HomeworkWeight: number,
        public TeacherName: string

        ) {}
}

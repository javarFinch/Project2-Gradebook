import { TeacherAssignment } from './teacher-assignment';
export class TeacherClass {
    constructor(
        public quizWeight: number,
        public participationAverage: number,
        public classSubject: string,
        public testWeight: number,
        public assignmentList: TeacherAssignment[],
        public quizAverage: number,
        public overAllAverage: number,
        public testAverage: number,
        public className: string,
        public homeworkAverage: number,
        public participationWeight: number,
        public homeworkWeight: number,
        public teacherName: string,
        public id: number

        ) {}
}

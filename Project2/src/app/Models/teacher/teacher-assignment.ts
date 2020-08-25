import { Grade } from './grade';


export class TeacherAssignment {
    constructor(
        public gradeList: Grade[],
        public dueDate: string,
        public totalPoints: number,
        public assignmentName: string,
        public assignmentType: string
        ) {}
}

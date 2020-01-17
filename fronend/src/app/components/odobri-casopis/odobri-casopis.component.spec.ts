import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OdobriCasopisComponent } from './odobri-casopis.component';

describe('OdobriCasopisComponent', () => {
  let component: OdobriCasopisComponent;
  let fixture: ComponentFixture<OdobriCasopisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OdobriCasopisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OdobriCasopisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
